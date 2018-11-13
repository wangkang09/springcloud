package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:05 2018/11/13
 * @Modified By:
 */
@Service
public class StudentService {
    @Resource
    ManService manService;
}
