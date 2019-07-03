package it.univaq.mobileprogramming.pharmacy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pharmacies")
public class Pharmacy {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "codasl")
    private String codasl;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "descr")
    private String descr;

    @ColumnInfo(name = "piva")
    private String piva;

    @ColumnInfo(name = "cap")
    private String cap;

    @ColumnInfo(name = "codcom")
    private String codcom;

    @ColumnInfo(name = "descrCom")
    private String descrCom;

    @ColumnInfo(name = "frazione")
    private String frazione;

    @ColumnInfo(name = "codprov")
    private String codprov;

    @ColumnInfo(name = "siglaProv")
    private String siglaProv;

    @ColumnInfo(name = "prov")
    private String prov;

    @ColumnInfo(name = "codReg")
    private String codReg;

    @ColumnInfo(name = "descrReg")
    private String descrReg;

    @ColumnInfo(name = "dateStart")
    private String dateStart;

    @ColumnInfo(name = "dateEnd")
    private String dateEnd;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "codType")
    private String codType;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "localize")
    private String localize;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodasl() {
        return codasl;
    }

    public void setCodasl(String codasl) {
        this.codasl = codasl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCodcom() {
        return codcom;
    }

    public void setCodcom(String codcom) {
        this.codcom = codcom;
    }

    public String getDescrCom() {
        return descrCom;
    }

    public void setDescrCom(String descrCom) {
        this.descrCom = descrCom;
    }

    public String getFrazione() {
        return frazione;
    }

    public void setFrazione(String frazione) {
        this.frazione = frazione;
    }

    public String getCodprov() {
        return codprov;
    }

    public void setCodprov(String codprov) {
        this.codprov = codprov;
    }

    public String getSiglaProv() {
        return siglaProv;
    }

    public void setSiglaProv(String siglaProv) {
        this.siglaProv = siglaProv;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCodReg() {
        return codReg;
    }

    public void setCodReg(String codReg) {
        this.codReg = codReg;
    }

    public String getDescrReg() {
        return descrReg;
    }

    public void setDescrReg(String descrReg) {
        this.descrReg = descrReg;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodType() {
        return codType;
    }

    public void setCodType(String codType) {
        this.codType = codType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocalize() {
        return localize;
    }

    public void setLocalize(String localize) {
        this.localize = localize;
    }


    public Pharmacy() {
    }

    @Ignore
    public Pharmacy(String descr, String prov, String x) {
        if (x.equals(this.descrCom)) {
            this.descr = descr;
            this.prov = prov;
        }
    }

    @Ignore
    public Pharmacy(String descr, String address, String piva, String codasl) {
        this.descr = descr;
        this.address = address;
        this.piva = piva;
        this.codasl = codasl;
    }
}
