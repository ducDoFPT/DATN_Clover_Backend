package com.datn.clover.services.sellers;

import com.datn.clover.entity.Evaluate;
import com.datn.clover.responeObject.EvaluateResponse;
import org.springframework.stereotype.Service;

@Service
public class EvaluateSellerService {
    public EvaluateResponse setResult(Evaluate evaluate) {
        EvaluateResponse response = new EvaluateResponse();
        response.setId(evaluate.getId());
        response.setContent(evaluate.getContent());
        response.setEvaluatesFeedbacks(evaluate.getEvaluatesFeedbacks());
        response.setEvaluateDay(evaluate.getEvaluateDay());
        response.setStarCount(evaluate.getStarCount());
        response.setProd(evaluate.getProd());
        return response;
    }
}
