package com.letovo.tableview;

public class Element {
    String id, shortName, name, mm, en, ec;

    public Element() {
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public Element(String id, String name, String mm, String en, String ec, String shortName) {
        this.id = id;
        this.shortName = shortName;
        this.name = name;
        this.mm = mm;
        this.en = en;
        this.ec = ec;
    }
}
