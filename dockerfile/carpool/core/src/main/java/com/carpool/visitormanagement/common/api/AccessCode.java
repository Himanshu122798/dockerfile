package com.carpool.visitormanagement.common.api;

import java.sql.Timestamp;

import com.carpool.general.common.api.ApplicationEntity;

public interface AccessCode extends ApplicationEntity {

  public String getCode();

  public void setCode(String code);

  public Timestamp getDateAndTime();

  public void setDateAndTime(Timestamp dateAndTime);

  public Long getVisitorId();

  public void setVisitorId(Long visitorId);

}
