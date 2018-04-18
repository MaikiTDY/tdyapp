package com.tdy.tdytravel.bean;

import java.util.List;

public class HomePage {
	private int retcode;
	private String retmsg;
	private List<homePageBean> data;

	/**
	 * @return the retcode
	 */
	public int getRetcode() {
		return retcode;
	}

	/**
	 * @param retcode
	 *            the retcode to set
	 */
	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	/**
	 * @return the retmsg
	 */
	public String getRetmsg() {
		return retmsg;
	}

	/**
	 * @param retmsg
	 *            the retmsg to set
	 */
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	/**
	 * @return the data
	 */
	public List<homePageBean> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<homePageBean> data) {
		this.data = data;
	}

	public class homePageBean {
		private int type;
		private String picurl;
		private String title;
		private int houseid;

		/**
		 * @return the type
		 */
		public int getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(int type) {
			this.type = type;
		}

		/**
		 * @return the picurl
		 */
		public String getPicurl() {
			return picurl;
		}

		/**
		 * @param picurl
		 *            the picurl to set
		 */
		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title
		 *            the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return the houseid
		 */
		public int getHouseid() {
			return houseid;
		}

		/**
		 * @param houseid
		 *            the houseid to set
		 */
		public void setHouseid(int houseid) {
			this.houseid = houseid;
		}

	}
}
