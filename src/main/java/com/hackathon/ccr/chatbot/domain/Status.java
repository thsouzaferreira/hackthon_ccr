package com.hackathon.ccr.chatbot.domain;


public enum Status implements EnumType {

    OPEN(1),
    CLOSED(2);

    private Integer id;

    private Status(Integer id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }


    public static class Converter extends EnumTypeConverter<Status> {
        public Converter() {
            super(Status.class);
        }
    }
}
