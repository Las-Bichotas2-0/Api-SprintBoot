package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.service.SubscriptionService;
import com.example.ilenguageapi.resource.SaveSubscriptionResource;
import com.example.ilenguageapi.resource.SubscriptionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private ModelMapper mapper;

    private Subscription convertToEntity(SaveSubscriptionResource resource){
        return mapper.map(resource, Subscription.class);
    }
    private SubscriptionResource convertToResource(Subscription entity){
        return mapper.map(entity, SubscriptionResource.class);
    }

    @GetMapping("/subscriptions")
    public Page<SubscriptionResource> getAllSubscriptions(Pageable pageable){
        List<SubscriptionResource> subscriptions = subscriptionService.getAllSubscriptions(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int subscriptionCount = subscriptions.size();
        return new PageImpl<>(subscriptions, pageable, subscriptionCount);
    }

    @PostMapping("/subscriptions")
    public SubscriptionResource createSubscription(@Valid @RequestBody SaveSubscriptionResource resource){
        return convertToResource(subscriptionService.createSubscription(convertToEntity(resource)));
    }

    @PutMapping ("subscriptions/{id}")
    public SubscriptionResource updateSubscription(@PathVariable(name="id") int subscriptionId, @Valid @RequestBody SaveSubscriptionResource resource){
        return convertToResource(subscriptionService.updateSubscription(subscriptionId,convertToEntity(resource)));
    }

    @DeleteMapping("subscriptions/{id}")
    public ResponseEntity<?> deleteSubscriptions(@PathVariable(name="id") int subscriptionId){
        return subscriptionService.deleteSubscription(subscriptionId);
    }

    @GetMapping("/subscriptions/{id}")
    public SubscriptionResource getSubscriptionById(@PathVariable(name="id") int subscriptionId){
        return convertToResource(subscriptionService.getBySubscriptionId(subscriptionId));
    }

    @GetMapping("/subscriptions/{name}")
    public SubscriptionResource getSubscriptionByPrice(@PathVariable(name="name") String subscriptionName){
        return convertToResource(subscriptionService.getByName(subscriptionName));
    }

    @GetMapping("/subscriptions/{price}")
    public SubscriptionResource getSubscriptionByPrice(@PathVariable(name="price") float producedPrice){
        return convertToResource(subscriptionService.getByPrice(producedPrice));
    }

    @GetMapping("/subscriptions/{monthDuration}")
    public SubscriptionResource getSubscriptionByMonthDuration(@PathVariable(name="monthDuration") int subscriptionMonthDuration){
    return convertToResource(subscriptionService.getByDuration(subscriptionMonthDuration));
    }


}