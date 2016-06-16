package com.example.test;

import java.util.List;

/**
 * Created by jyd-pc9052 on 16/6/14.
 */
public class Order {

    /**
     * id : 575eb7565b62821191799d37
     * orderno : 20160613213830622047
     * consignee : 测试
     * address : 河南省郑州市金水区商都路在建业五栋大楼附近
     * telephone : 13674919601
     * totalcost : 0.01
     * fright : 0
     * paymode : 1
     * paycost : 0.01
     * customer_integral : 0
     * useintegral : 0
     * useintegral_cost : 0
     * goodsvalue : 0.01
     * getintegral : 0
     * totalquantity : 1
     * created_at : 2016-06-13 21:38:30
     * ordergoods : [{"product_id":"56986f1aaf4843084c0008c3","qrcode":"6901382035243","specification":"500ml*1/瓶；50°","title":"50°五粮醇红淡雅陶瓶装 500 ml","price":0.01,"integral":6900,"quantity":1,"avatar_url":"http://img.51xiaoniu.cn/product/main_assets/assets/570d/5a4e/206a/af31/672a/1303/56986f24af484307c4000629.jpg@!avatar","is_gift":false,"state":"online"}]
     * coupons : []
     * getcoupons : []
     * workflow_state : refunding
     * lng : 113.738115
     * lat : 34.753139
     * remarks :
     * delivery_real_name : null
     * delivery_user_desc : null
     * delivery_mobile : null
     * store_name : 通泰路
     * temp_consignee :
     * temp_telephone :
     * is_comment : null
     * RefundFlowings : [{"refundcost":0.01,"refundaccount":"2231106695@qq.com","refund_batch_no":null,"refundstate":null,"isuseragree":null,"userreason":"123","userinforeason":null,"istwoaudit":"0","applytwoaudittime":null,"oneaudittime":null,"twoaudittime":null,"isrefundover":"0","endtime":null,"userinfo":{"id":"56c45924c2fb4e2050000022","pdistance":0,"shopname":"郑州酒运达电子商务有限公司","location":null,"lowestprice":19,"fright":0,"fright_time":null,"night_time":null,"start_business":"09:00","end_business":"23:00","h_lowestprice":0,"h_fright":0,"work_24":"false","help_telephone":"4008609519"}}]
     * online_paid : 1
     */

    private String id;
    private String orderno;
    private String consignee;
    private String address;
    private String telephone;
    private double totalcost;
    private int fright;
    private int paymode;
    private double paycost;
    private int customer_integral;
    private int useintegral;
    private int useintegral_cost;
    private double goodsvalue;
    private int getintegral;
    private int totalquantity;
    private String created_at;
    private String workflow_state;
    private String lng;
    private String lat;
    private String remarks;
    private Object delivery_real_name;
    private Object delivery_user_desc;
    private Object delivery_mobile;
    private String store_name;
    private String temp_consignee;
    private String temp_telephone;
    private Object is_comment;
    private int online_paid;
    /**
     * product_id : 56986f1aaf4843084c0008c3
     * qrcode : 6901382035243
     * specification : 500ml*1/瓶；50°
     * title : 50°五粮醇红淡雅陶瓶装 500 ml
     * price : 0.01
     * integral : 6900
     * quantity : 1
     * avatar_url : http://img.51xiaoniu.cn/product/main_assets/assets/570d/5a4e/206a/af31/672a/1303/56986f24af484307c4000629.jpg@!avatar
     * is_gift : false
     * state : online
     */

    private List<OrdergoodsEntity> ordergoods;
    private List<?> coupons;
    private List<?> getcoupons;
    /**
     * refundcost : 0.01
     * refundaccount : 2231106695@qq.com
     * refund_batch_no : null
     * refundstate : null
     * isuseragree : null
     * userreason : 123
     * userinforeason : null
     * istwoaudit : 0
     * applytwoaudittime : null
     * oneaudittime : null
     * twoaudittime : null
     * isrefundover : 0
     * endtime : null
     * userinfo : {"id":"56c45924c2fb4e2050000022","pdistance":0,"shopname":"郑州酒运达电子商务有限公司","location":null,"lowestprice":19,"fright":0,"fright_time":null,"night_time":null,"start_business":"09:00","end_business":"23:00","h_lowestprice":0,"h_fright":0,"work_24":"false","help_telephone":"4008609519"}
     */

    private Object RefundFlowings;

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public void setFright(int fright) {
        this.fright = fright;
    }

    public void setPaymode(int paymode) {
        this.paymode = paymode;
    }

    public void setPaycost(double paycost) {
        this.paycost = paycost;
    }

    public void setCustomer_integral(int customer_integral) {
        this.customer_integral = customer_integral;
    }

    public void setUseintegral(int useintegral) {
        this.useintegral = useintegral;
    }

    public void setUseintegral_cost(int useintegral_cost) {
        this.useintegral_cost = useintegral_cost;
    }

    public void setGoodsvalue(double goodsvalue) {
        this.goodsvalue = goodsvalue;
    }

    public void setGetintegral(int getintegral) {
        this.getintegral = getintegral;
    }

    public void setTotalquantity(int totalquantity) {
        this.totalquantity = totalquantity;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setWorkflow_state(String workflow_state) {
        this.workflow_state = workflow_state;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDelivery_real_name(Object delivery_real_name) {
        this.delivery_real_name = delivery_real_name;
    }

    public void setDelivery_user_desc(Object delivery_user_desc) {
        this.delivery_user_desc = delivery_user_desc;
    }

    public void setDelivery_mobile(Object delivery_mobile) {
        this.delivery_mobile = delivery_mobile;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setTemp_consignee(String temp_consignee) {
        this.temp_consignee = temp_consignee;
    }

    public void setTemp_telephone(String temp_telephone) {
        this.temp_telephone = temp_telephone;
    }

    public void setIs_comment(Object is_comment) {
        this.is_comment = is_comment;
    }

    public void setOnline_paid(int online_paid) {
        this.online_paid = online_paid;
    }

    public void setOrdergoods(List<OrdergoodsEntity> ordergoods) {
        this.ordergoods = ordergoods;
    }

    public void setCoupons(List<?> coupons) {
        this.coupons = coupons;
    }

    public void setGetcoupons(List<?> getcoupons) {
        this.getcoupons = getcoupons;
    }

    public void setRefundFlowings(Object RefundFlowings) {
        this.RefundFlowings = RefundFlowings;
    }

    public String getId() {
        return id;
    }

    public String getOrderno() {
        return orderno;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public int getFright() {
        return fright;
    }

    public int getPaymode() {
        return paymode;
    }

    public double getPaycost() {
        return paycost;
    }

    public int getCustomer_integral() {
        return customer_integral;
    }

    public int getUseintegral() {
        return useintegral;
    }

    public int getUseintegral_cost() {
        return useintegral_cost;
    }

    public double getGoodsvalue() {
        return goodsvalue;
    }

    public int getGetintegral() {
        return getintegral;
    }

    public int getTotalquantity() {
        return totalquantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getWorkflow_state() {
        return workflow_state;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getRemarks() {
        return remarks;
    }

    public Object getDelivery_real_name() {
        return delivery_real_name;
    }

    public Object getDelivery_user_desc() {
        return delivery_user_desc;
    }

    public Object getDelivery_mobile() {
        return delivery_mobile;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getTemp_consignee() {
        return temp_consignee;
    }

    public String getTemp_telephone() {
        return temp_telephone;
    }

    public Object getIs_comment() {
        return is_comment;
    }

    public int getOnline_paid() {
        return online_paid;
    }

    public List<OrdergoodsEntity> getOrdergoods() {
        return ordergoods;
    }

    public List<?> getCoupons() {
        return coupons;
    }

    public List<?> getGetcoupons() {
        return getcoupons;
    }

    public Object getRefundFlowings() {
        return RefundFlowings;
    }

    public class OrdergoodsEntity {
        private String product_id;
        private String qrcode;
        private String specification;
        private String title;
        private double price;
        private int integral;
        private int quantity;
        private String avatar_url;
        private boolean is_gift;
        private String state;

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setIs_gift(boolean is_gift) {
            this.is_gift = is_gift;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getProduct_id() {
            return product_id;
        }

        public String getQrcode() {
            return qrcode;
        }

        public String getSpecification() {
            return specification;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public int getIntegral() {
            return integral;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public boolean isIs_gift() {
            return is_gift;
        }

        public String getState() {
            return state;
        }
    }

    public static class RefundFlowingsEntity {
        private double refundcost;
        private String refundaccount;
        private Object refund_batch_no;
        private Object refundstate;
        private Object isuseragree;
        private String userreason;
        private Object userinforeason;
        private String istwoaudit;
        private Object applytwoaudittime;
        private Object oneaudittime;
        private Object twoaudittime;
        private String isrefundover;
        private Object endtime;
        /**
         * id : 56c45924c2fb4e2050000022
         * pdistance : 0
         * shopname : 郑州酒运达电子商务有限公司
         * location : null
         * lowestprice : 19
         * fright : 0
         * fright_time : null
         * night_time : null
         * start_business : 09:00
         * end_business : 23:00
         * h_lowestprice : 0
         * h_fright : 0
         * work_24 : false
         * help_telephone : 4008609519
         */

        private UserinfoEntity userinfo;

        public void setRefundcost(double refundcost) {
            this.refundcost = refundcost;
        }

        public void setRefundaccount(String refundaccount) {
            this.refundaccount = refundaccount;
        }

        public void setRefund_batch_no(Object refund_batch_no) {
            this.refund_batch_no = refund_batch_no;
        }

        public void setRefundstate(Object refundstate) {
            this.refundstate = refundstate;
        }

        public void setIsuseragree(Object isuseragree) {
            this.isuseragree = isuseragree;
        }

        public void setUserreason(String userreason) {
            this.userreason = userreason;
        }

        public void setUserinforeason(Object userinforeason) {
            this.userinforeason = userinforeason;
        }

        public void setIstwoaudit(String istwoaudit) {
            this.istwoaudit = istwoaudit;
        }

        public void setApplytwoaudittime(Object applytwoaudittime) {
            this.applytwoaudittime = applytwoaudittime;
        }

        public void setOneaudittime(Object oneaudittime) {
            this.oneaudittime = oneaudittime;
        }

        public void setTwoaudittime(Object twoaudittime) {
            this.twoaudittime = twoaudittime;
        }

        public void setIsrefundover(String isrefundover) {
            this.isrefundover = isrefundover;
        }

        public void setEndtime(Object endtime) {
            this.endtime = endtime;
        }

        public void setUserinfo(UserinfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public double getRefundcost() {
            return refundcost;
        }

        public String getRefundaccount() {
            return refundaccount;
        }

        public Object getRefund_batch_no() {
            return refund_batch_no;
        }

        public Object getRefundstate() {
            return refundstate;
        }

        public Object getIsuseragree() {
            return isuseragree;
        }

        public String getUserreason() {
            return userreason;
        }

        public Object getUserinforeason() {
            return userinforeason;
        }

        public String getIstwoaudit() {
            return istwoaudit;
        }

        public Object getApplytwoaudittime() {
            return applytwoaudittime;
        }

        public Object getOneaudittime() {
            return oneaudittime;
        }

        public Object getTwoaudittime() {
            return twoaudittime;
        }

        public String getIsrefundover() {
            return isrefundover;
        }

        public Object getEndtime() {
            return endtime;
        }

        public UserinfoEntity getUserinfo() {
            return userinfo;
        }

        public static class UserinfoEntity {
            private String id;
            private int pdistance;
            private String shopname;
            private Object location;
            private int lowestprice;
            private int fright;
            private Object fright_time;
            private Object night_time;
            private String start_business;
            private String end_business;
            private int h_lowestprice;
            private int h_fright;
            private String work_24;
            private String help_telephone;

            public void setId(String id) {
                this.id = id;
            }

            public void setPdistance(int pdistance) {
                this.pdistance = pdistance;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public void setLowestprice(int lowestprice) {
                this.lowestprice = lowestprice;
            }

            public void setFright(int fright) {
                this.fright = fright;
            }

            public void setFright_time(Object fright_time) {
                this.fright_time = fright_time;
            }

            public void setNight_time(Object night_time) {
                this.night_time = night_time;
            }

            public void setStart_business(String start_business) {
                this.start_business = start_business;
            }

            public void setEnd_business(String end_business) {
                this.end_business = end_business;
            }

            public void setH_lowestprice(int h_lowestprice) {
                this.h_lowestprice = h_lowestprice;
            }

            public void setH_fright(int h_fright) {
                this.h_fright = h_fright;
            }

            public void setWork_24(String work_24) {
                this.work_24 = work_24;
            }

            public void setHelp_telephone(String help_telephone) {
                this.help_telephone = help_telephone;
            }

            public String getId() {
                return id;
            }

            public int getPdistance() {
                return pdistance;
            }

            public String getShopname() {
                return shopname;
            }

            public Object getLocation() {
                return location;
            }

            public int getLowestprice() {
                return lowestprice;
            }

            public int getFright() {
                return fright;
            }

            public Object getFright_time() {
                return fright_time;
            }

            public Object getNight_time() {
                return night_time;
            }

            public String getStart_business() {
                return start_business;
            }

            public String getEnd_business() {
                return end_business;
            }

            public int getH_lowestprice() {
                return h_lowestprice;
            }

            public int getH_fright() {
                return h_fright;
            }

            public String getWork_24() {
                return work_24;
            }

            public String getHelp_telephone() {
                return help_telephone;
            }
        }
    }
}
