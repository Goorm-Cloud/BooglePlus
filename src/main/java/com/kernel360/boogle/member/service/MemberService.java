package com.kernel360.boogle.member.service;

import com.kernel360.boogle.global.error.code.MemberErrorCode;
import com.kernel360.boogle.global.error.exception.BusinessException;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.member.db.MemberRole;
import com.kernel360.boogle.member.model.MemberDataDTO;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import com.kernel360.boogle.mypage.model.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberEntity signup(MemberSignupDTO member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new BusinessException(MemberErrorCode.ALREADY_SIGNED_UP_MEMBER);
        }

        return memberRepository.save(MemberEntity.builder()
                .email(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .role(MemberRole.ROLE_USER)
                .build());
    }
    @Transactional
    public MemberEntity signupAdmin(MemberSignupDTO member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new BusinessException(MemberErrorCode.ALREADY_SIGNED_UP_MEMBER);
        }

        return memberRepository.save(MemberEntity.builder()
                .email(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .role(MemberRole.ROLE_ADMIN)
                .build());
    }

    @Transactional
    public void updateMemberInfo(MemberRequestDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findByEmail(memberDTO.getEmail());

        memberEntity.setName(memberDTO.getName());
        memberEntity.setNickname(memberDTO.getNickname());
        memberEntity.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPhoneNumber(memberDTO.getPhoneNumber());
    }
    @Transactional(readOnly = true)
    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public MemberEntity findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public MemberDataDTO findByIdForDashboard(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        return MemberDataDTO.builder()
                .email(memberEntity.getEmail())
                .name(memberEntity.getName())
                .nickname(memberEntity.getNickname())
                .build();
    }
}