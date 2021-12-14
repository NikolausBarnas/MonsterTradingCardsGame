package com.mtcg.repository.directive;

public interface Directive {
    int handlePOST();
    String handleGET();
    int handlePUT();
    int handleDELETE();
}
