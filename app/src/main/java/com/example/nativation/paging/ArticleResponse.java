package com.example.nativation.paging;

import java.util.IdentityHashMap;
import java.util.List;

/**
 * 作者：Create on 2020/4/2 20:14  by  wzl
 * 描述：
 * 最近修改：2020/4/2 20:14 modify by wzl
 */
public class ArticleResponse {

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private DataBean data;

    private int errorcode;

    private String errormsg;


    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getErrormsg() {
        return errormsg;
    }



    public class DataBean {

        private int curpage;

        private List<DatasBean> datas;
        private int offset;
        private boolean over;
        private int pagecount;
        private int size;
        private int total;

        public void setCurpage(int curpage) {
            this.curpage = curpage;
        }

        public int getCurpage() {
            return curpage;
        }



        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return over;
        }

        public void setPagecount(int pagecount) {
            this.pagecount = pagecount;
        }

        public int getPagecount() {
            return pagecount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }
        public class DatasBean {
            private String apklink;
            private int audit;
            private String author;
            private boolean canedit;
            private int chapterid;
            private String chaptername;
            private boolean collect;
            private int courseid;
            private String desc;
            private String descmd;
            private String envelopepic;
            private boolean fresh;
            private int id;
            private String link;
            private String nicedate;
            private String nicesharedate;
            private String origin;
            private String prefix;
            private String projectlink;
            private int publishtime;
            private int selfvisible;
            private int sharedate;
            private String shareuser;
            private int superchapterid;
            private String superchaptername;

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }

            private List<?> tags;
            private String title;
            private int type;
            private int userid;
            private int visible;
            private int zan;

            public void setApklink(String apklink) {
                this.apklink = apklink;
            }

            public String getApklink() {
                return apklink;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public int getAudit() {
                return audit;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAuthor() {
                return author;
            }

            public void setCanedit(boolean canedit) {
                this.canedit = canedit;
            }

            public boolean getCanedit() {
                return canedit;
            }

            public void setChapterid(int chapterid) {
                this.chapterid = chapterid;
            }

            public int getChapterid() {
                return chapterid;
            }

            public void setChaptername(String chaptername) {
                this.chaptername = chaptername;
            }

            public String getChaptername() {
                return chaptername;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public boolean getCollect() {
                return collect;
            }

            public void setCourseid(int courseid) {
                this.courseid = courseid;
            }

            public int getCourseid() {
                return courseid;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDesc() {
                return desc;
            }

            public void setDescmd(String descmd) {
                this.descmd = descmd;
            }

            public String getDescmd() {
                return descmd;
            }

            public void setEnvelopepic(String envelopepic) {
                this.envelopepic = envelopepic;
            }

            public String getEnvelopepic() {
                return envelopepic;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public boolean getFresh() {
                return fresh;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getLink() {
                return link;
            }

            public void setNicedate(String nicedate) {
                this.nicedate = nicedate;
            }

            public String getNicedate() {
                return nicedate;
            }

            public void setNicesharedate(String nicesharedate) {
                this.nicesharedate = nicesharedate;
            }

            public String getNicesharedate() {
                return nicesharedate;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getOrigin() {
                return origin;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setProjectlink(String projectlink) {
                this.projectlink = projectlink;
            }

            public String getProjectlink() {
                return projectlink;
            }

            public void setPublishtime(int publishtime) {
                this.publishtime = publishtime;
            }

            public int getPublishtime() {
                return publishtime;
            }

            public void setSelfvisible(int selfvisible) {
                this.selfvisible = selfvisible;
            }

            public int getSelfvisible() {
                return selfvisible;
            }

            public void setSharedate(int sharedate) {
                this.sharedate = sharedate;
            }

            public int getSharedate() {
                return sharedate;
            }

            public void setShareuser(String shareuser) {
                this.shareuser = shareuser;
            }

            public String getShareuser() {
                return shareuser;
            }

            public void setSuperchapterid(int superchapterid) {
                this.superchapterid = superchapterid;
            }

            public int getSuperchapterid() {
                return superchapterid;
            }

            public void setSuperchaptername(String superchaptername) {
                this.superchaptername = superchaptername;
            }

            public String getSuperchaptername() {
                return superchaptername;
            }



            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return title;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getUserid() {
                return userid;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getVisible() {
                return visible;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public int getZan() {
                return zan;
            }

        }
    }
}