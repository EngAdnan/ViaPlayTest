package com.viaplay.test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViaplayResponse {

    @SerializedName("_links")
    @Expose
    private viaplay sections;

    public List<ViaplaySections> getSections() {
        return sections.sectionsList;
    }
    public void setSections(List<ViaplaySections> sectionsList) {
        this.sections.sectionsList = sectionsList;
    }


    private static class viaplay {

        @SerializedName("viaplay:sections")
        @Expose
        private List<ViaplaySections> sectionsList;

    }

}
