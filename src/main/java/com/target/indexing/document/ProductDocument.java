package com.target.indexing.document;

import com.target.indexing.model.Date;
import com.target.indexing.model.Metafield;
import com.target.indexing.model.Price;

import java.util.List;
import java.util.Map;

public class ProductDocument {
    private String product_id;
    private String sellerId;
    private String title;
    private String pageTitle;
    private String description;
    private String manufacturer;
    private Price price;
    private boolean isLowQuantity;
    private boolean isSoldOut;
    private boolean isBackorder;
    private List<Metafield> metafields;
    private boolean requiresShipping;
    private boolean isVisible;
    private Date publishedAt;
    private Date createdAt;
    private Date updatedAt;
    private Map<String, String> workflow;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public boolean getisLowQuantity() {
        return isLowQuantity;
    }

    public void setisLowQuantity(boolean isLowQuantity) {
        this.isLowQuantity = isLowQuantity;
    }

    public boolean getisSoldOut() {
        return isSoldOut;
    }

    public void setisSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public boolean getisBackorder() {
        return isBackorder;
    }

    public void setisBackorder(boolean backorder) {
        isBackorder = backorder;
    }

    public List<Metafield> getMetafields() {
        return metafields;
    }

    public void setMetafields(List<Metafield> metafields) {
        this.metafields = metafields;
    }

    public boolean isRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public boolean getisVisible() {
        return isVisible;
    }

    public void setisVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, String> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Map<String, String> workflow) {
        this.workflow = workflow;
    }
}
