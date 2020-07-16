package com.mint.task1;

import com.mint.task1.data.CardInfo;
import com.mint.task1.model.BinlistResponse;
import com.mint.task1.model.CardInfoDto;
import com.mint.task1.model.CountResponse;
import com.mint.task1.model.KafkaObject;
import com.mint.task1.model.Payload;
import com.mint.task1.repository.CardInfoRepostiory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger logger = LogManager.getLogger(TaskService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CardInfoRepostiory cardInfoRepostiory;

    @Autowired
    private Util util;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Value("${binlist_url}")
    private String binlistUrl;

    public CardInfoDto verifyCard(String cardId) {

        Optional<CardInfo> cardInfoOptional = cardInfoRepostiory.findByCardNumber(cardId);

        if (cardInfoOptional.isPresent()) {
            CardInfo cardInfo = cardInfoOptional.get();
            cardInfo.setCount(cardInfo.getCount() + 1);
            cardInfoRepostiory.save(cardInfo);
            return util.convertCardInfoToDto(cardInfo);
        }

        ResponseEntity<BinlistResponse> binlistResponseEntity = restTemplate.getForEntity(binlistUrl + cardId, BinlistResponse.class);

        CardInfoDto cardInfoDto = new CardInfoDto();
        if (!String.valueOf(binlistResponseEntity.getStatusCode().value()).startsWith("2")) {
            cardInfoDto.setSuccess(false);
            return cardInfoDto;
        }

        BinlistResponse binlistResponse = binlistResponseEntity.getBody();

        CardInfo cardInfo = util.buildNewCardInfoObject(binlistResponse, cardId);

        KafkaObject kafkaObject = new KafkaObject();
        kafkaObject.setBank(cardInfo.getBank());
        kafkaObject.setScheme(cardInfo.getScheme());
        kafkaObject.setType(cardInfo.getType());

        kafkaProducerService.pubishToTask3(kafkaObject);

        cardInfoRepostiory.save(cardInfo);

        Payload payload = new Payload();
        payload.setBank(binlistResponse.getBank().getName());
        payload.setScheme(binlistResponse.getScheme());
        payload.setType(binlistResponse.getType());

        cardInfoDto.setSuccess(true);
        cardInfoDto.setPayload(payload);
        return cardInfoDto;
    }



    public CountResponse getNumberOfHits(Integer start, Integer limit) {

        Pageable requiredPageStartAndLimit = PageRequest.of(start, limit);

        Iterable<CardInfo> cardInfoIterable = cardInfoRepostiory.findAll();

        Page<CardInfo> cardInfoPage = cardInfoRepostiory.findAll(requiredPageStartAndLimit);

        CountResponse countResponse = new CountResponse();

        if (cardInfoPage.isEmpty()) {
            countResponse.setSuccess(false);
            countResponse.setStart(start);
            countResponse.setLimit(limit);
            return countResponse;
        }

        List<CardInfo> cardInfoList = cardInfoPage.toList();

        logger.info(cardInfoList);

        countResponse = util.buildCountResponse(cardInfoList, start, limit);

        return countResponse;
    }


}
