package com.mint.task1;

import com.mint.task1.data.CardInfo;
import com.mint.task1.model.BinlistResponse;
import com.mint.task1.model.CardInfoDto;
import com.mint.task1.model.CountResponse;
import com.mint.task1.model.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Util {

    public static int newRecordCount = 1;

    public CardInfoDto convertCardInfoToDto(CardInfo cardInfo) {
        CardInfoDto cardInfoDto = new CardInfoDto();
        cardInfoDto.setSuccess(true);

        Payload payload = new Payload();
        payload.setType(cardInfo.getType());
        payload.setScheme(cardInfo.getScheme());
        payload.setBank(cardInfo.getBank());
        cardInfoDto.setPayload(payload);

        return cardInfoDto;
    }

    public CardInfo buildNewCardInfoObject(BinlistResponse binlistResponse, String cardId) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setBank(binlistResponse.getBank().getName());
        cardInfo.setBrand(binlistResponse.getBrand());
        cardInfo.setCardNumber(cardId);
        cardInfo.setCount(Util.newRecordCount);
        cardInfo.setCountry(binlistResponse.getCountry().getName());
        cardInfo.setScheme(binlistResponse.getScheme());
        cardInfo.setType(binlistResponse.getType());

        return cardInfo;
    }

    public CountResponse buildCountResponse(List<CardInfo> cardInfoList, Integer start, Integer limit) {
        Map<String, Integer> countMap = new HashMap<>();

        for (CardInfo cardInfo : cardInfoList) {
            countMap.put(cardInfo.getCardNumber(), cardInfo.getCount());
        }

        CountResponse countResponse = new CountResponse();
        countResponse.setSuccess(true);
        countResponse.setLimit(limit);
        countResponse.setStart(start);
        countResponse.setPayload(countMap);

        return countResponse;
    }

}
