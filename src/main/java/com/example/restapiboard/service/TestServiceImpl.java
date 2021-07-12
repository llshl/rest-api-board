package com.example.restapiboard.service;

import com.example.restapiboard.repository.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    private TestMapper testMapper;

    @Autowired
    TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public List<Map<String, Object>> getTest() {
        return testMapper.getTest();
    }
}