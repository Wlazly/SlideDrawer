package com.github.greendao.bean.device;

/**
 * Created by wzhiqiang on 2019/1/14.
 */

public class TodoBean {
    public String content;
    public long end;
    public int repeat;
    public long start;
    public String topic;

    public TodoBean() {
    }

    public TodoBean(String content, long end, int repeat, long start, String topic) {
        this.content = content;
        this.end = end;
        this.repeat = repeat;
        this.start = start;
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
