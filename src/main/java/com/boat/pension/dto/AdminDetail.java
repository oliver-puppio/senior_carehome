package com.boat.pension.dto;

import com.boat.pension.model.Admin;
import com.boat.pension.model.Institution;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class AdminDetail {
    private Integer adminID;
    private String name;
    private String address;
    private String firm_name;
    private String leader;
    private String tel;
    private String province;
    private String city;
    private String area;
    private String detail;

    public AdminDetail(Admin admin, Institution institution) {
        adminID = admin.getId();
        name = admin.getCname();
        if (institution.getId() != null) {
            address = institution.getLoc();
            String[] loc_temp = institution.getLoc().split("-");
            List<String> loc_split = Arrays.asList(loc_temp);
            if (loc_split.size() == 3) {
                loc_split.add(0, loc_split.get(0));
            }
            province = loc_split.get(0);
            city = loc_split.get(1);
            area = loc_split.get(2);
            detail = loc_split.get(3);
            firm_name = institution.getName();
            leader = institution.getLeader();
            tel = institution.getTel();
        }
    }
}
