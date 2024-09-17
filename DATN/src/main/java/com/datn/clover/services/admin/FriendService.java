package com.datn.clover.services.admin;


import com.datn.clover.entity.Friend;
import com.datn.clover.inter.FriendJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendJPA friendJPA;

    public List<Friend> getAllFriends() {
        return friendJPA.findAll();
    }
}
