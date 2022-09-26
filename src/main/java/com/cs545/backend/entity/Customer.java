package com.cs545.backend.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("customer")
@Data
public class Customer extends User {
    @OneToMany(mappedBy = "requester")
    private List<Request> requests = new ArrayList<>();

    public void setRequests(List<Request> requests){
        this.requests = requests;
        for(Request request: requests){
            if(!this.equals(request.getRequester())){
                request.setRequester(this);
            }
        }
    }
    public void addRequest(Request request){
        if(request != null){
            this.requests.add(request);
            if(!this.equals(request.getRequester())){
                request.setRequester(this);
            }
        }
    }
}