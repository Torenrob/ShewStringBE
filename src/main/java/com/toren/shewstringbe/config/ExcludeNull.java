package com.toren.shewstringbe.config;

@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
public class ExcludeNull {

    @Override
    public boolean equals(Object object) {
        return object == null;
    }
}
