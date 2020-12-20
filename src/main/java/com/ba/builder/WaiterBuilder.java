package com.ba.builder;

import com.ba.entity.Media;
import com.ba.entity.Waiter;

public class WaiterBuilder extends Builder {

    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private String urlToImage;
    private Long salary;
    private Media media;

    public WaiterBuilder id(Long id) {
        this.setId(id);
        return this;
    }

    public WaiterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WaiterBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public WaiterBuilder mail(String mail) {
        this.mail = mail;
        return this;
    }

    public WaiterBuilder address(String address) {
        this.address = address;
        return this;
    }

    public WaiterBuilder urlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public WaiterBuilder salary(Long salary) {
        this.salary = salary;
        return this;
    }

    public WaiterBuilder media(Media media) {
        this.media = media;
        return this;
    }

    @Override
    public Waiter build() {
        Waiter waiter = new Waiter();

        waiter.setId(this.getId());
        waiter.setName(this.name);
        waiter.setPhoneNumber(this.phoneNumber);
        waiter.setMail(this.mail);
        waiter.setAddress(this.address);
        waiter.setUrlToImage(this.urlToImage);
        waiter.setSalary(this.salary);
        waiter.setMedia(this.media);

        return waiter;
    }
}
