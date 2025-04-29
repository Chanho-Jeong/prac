package com.example.login.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.login.dto.MemberDTO;

@Repository
public class MemberRepository {

    private final List<MemberDTO> memberList = new ArrayList<>();

    public void save(MemberDTO memberDTO) {
        memberList.add(memberDTO);
    }

    public MemberDTO findByUserId(String userid){
        return memberList.stream().filter(m->m.getUserid().equals(userid))
        .findFirst().orElse(null);

    }

    public List<MemberDTO> findAll(){
        return new ArrayList<>(memberList);
    }
    
}
