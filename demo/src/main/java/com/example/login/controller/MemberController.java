package com.example.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.login.dto.MemberDTO;
import com.example.login.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;

        System.out.println("🔥 MemberController 생성됨!");
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        
        if (memberDTO.getUserid() == null || memberDTO.getUserid().isBlank()) {

            redirectAttributes.addFlashAttribute("error", "아이디를 입력하세요요");
           
            System.out.println("아이디 비여있다");
           
            return "redirect:/member/signup";
        }
        if (memberDTO.getPassword() == null || memberDTO.getPassword().isBlank()) {
            redirectAttributes.addFlashAttribute("error","비밀번호를 입력하세요");
            
            System.out.println(" 비번 비어있다");
            
            return "redirect:/member/signup";
            
        }

        if (memberRepository.findByUserId(memberDTO.getUserid()) !=null) {
            redirectAttributes.addFlashAttribute("error","이미존재하는id");
            return "redirect:/member/signup";
        }


        memberRepository.save(memberDTO);

        redirectAttributes.addFlashAttribute("msg", "회원가입완료");

        

        return "redirect:/member/members";
        
        
    }
    @GetMapping("/test")
    public String test() {
        return "test"; // templates/test.html
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        
        MemberDTO member = memberRepository.findByUserId(memberDTO.getUserid());
        
       if(member == null || !member.getPassword().equals(memberDTO.getPassword())){
            redirectAttributes.addFlashAttribute("error", "아이디 or 비번오류");
            return "redirect:/member/login";
        }

        httpSession.setAttribute("loginUser", member);

        return "redirect:/member/members";
    }
    

    @GetMapping("/members")
    public String members(Model model){
        model.addAttribute("members", memberRepository.findAll());

        return "members";
    }
    

}
