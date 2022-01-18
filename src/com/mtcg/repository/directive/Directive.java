package com.mtcg.repository.directive;

public interface Directive {
    int handlePost(String body);
    String handleGet(String body);
    int handlePut(String body);
    int handleDelete(String body);
}
