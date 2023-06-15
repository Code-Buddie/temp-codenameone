package com.csus.csc133.models;

import java.util.Date;

public class Lecture {
	// current lecture hall
    private LectureHall hall;
    
    // lecture end time
    private Date endTime;

    public Lecture(LectureHall hall, Date endTime) {
        this.hall = hall;
        this.endTime = endTime;
    }

    public LectureHall getHall() {
        return hall;
    }

    public void setHall(LectureHall hall) {
        this.hall = hall;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
