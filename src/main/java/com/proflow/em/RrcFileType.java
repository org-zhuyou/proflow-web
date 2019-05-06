package com.proflow.em;

/**
 * Created by Leonid on 2019/5/6.
 */
public enum RrcFileType {
    IMAGE(1, "图片"), VIDEO(2, "视频"), OTHER(3, "其他");

    private Integer id;
    private String name;

    private RrcFileType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
