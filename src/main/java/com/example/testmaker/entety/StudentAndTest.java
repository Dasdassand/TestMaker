package com.example.testmaker.entety;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@RequiredArgsConstructor
public class StudentAndTest {
    private final User user;
    private final List<Test> tests = new ArrayList<>();
}
