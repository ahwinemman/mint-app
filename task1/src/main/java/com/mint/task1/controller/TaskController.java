package com.mint.task1.controller;

import com.mint.task1.TaskService;
import com.mint.task1.model.CardInfoDto;
import com.mint.task1.model.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/card-scheme")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/verify/{cardId}")
    public CardInfoDto verifycard(@PathVariable("cardId") String cardId) {
        return taskService.verifyCard(cardId);
    }

    @GetMapping(value = "/stats")
    public CountResponse getNumberOfHits(@PathParam("start") Integer start, @PathParam("limit") Integer limit) {

        return taskService.getNumberOfHits(start, limit);
    }

}
