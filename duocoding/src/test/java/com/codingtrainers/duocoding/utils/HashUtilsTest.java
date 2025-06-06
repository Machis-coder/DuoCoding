package com.codingtrainers.duocoding.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashUtilsTest {
    @Test
    public void testSha2() {
        String input = "abcd";
        String expected = "88d4266fd4e6338d13b845fcf289579d209c897823b9217da3e161936f031589";
        String output = HashUtils.sha256(input);
        Assertions.assertEquals(expected, output);
    }

}
