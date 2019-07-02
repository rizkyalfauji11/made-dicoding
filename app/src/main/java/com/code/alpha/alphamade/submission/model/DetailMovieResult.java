
package com.code.alpha.alphamade.submission.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class DetailMovieResult {

    @SerializedName("production_companies")
    private ArrayList<ProductionCompany> productionCompanies;

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

}
