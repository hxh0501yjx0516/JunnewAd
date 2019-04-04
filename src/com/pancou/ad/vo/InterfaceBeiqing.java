package com.pancou.ad.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterfaceBeiqing {

	private int _id;
	private int id;
	private int uid;
	private int gid;
	private int sid;
	private String gunion;
	private double amt;
	private int action;
	private String indbdate;
	private String webMasterName;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getGunion() {
		return gunion;
	}

	public void setGunion(String gunion) {
		this.gunion = gunion;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getIndbdate() {
		return indbdate;
	}

	public void setIndbdate(String indbdate) {
		this.indbdate = indbdate;
	}

	public String getWebMasterName() {
		return webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}

	public static List<InterfaceBeiqing> parseJson(String jsonString) {
		List<InterfaceBeiqing> list = new ArrayList<InterfaceBeiqing>();
		try {
			JSONArray array = new JSONArray(jsonString);
			for (int i = 0; i < array.length(); i++) {
				InterfaceBeiqing bq = new InterfaceBeiqing();
				JSONObject object = array.optJSONObject(i);

				Field[] fields = InterfaceBeiqing.class.getDeclaredFields();
				for (Field f : fields) {
					f.setAccessible(true);
					if (f.getName().equals("uid")) {
						f.set(bq,
								Integer.parseInt(object.get(f.getName()) + ""));
					}
					if (f.getName().equals("gid")) {
						f.set(bq,
								Integer.parseInt(object.get(f.getName()) + ""));
					}
					if (f.getName().equals("sid")) {
						f.set(bq,
								Integer.parseInt(object.get(f.getName()) + ""));
					}
					if (f.getName().equals("gunion")) {
						String gunion = object.get(f.getName()) + "";
						if (gunion.equals("*_--_*")) {
							gunion = "0";
						}
						// Ŀǰ����IDΪ5λ��
						if (gunion.length() > 5) {
							gunion = gunion.substring(0, 5);
						}
						f.set(bq, gunion);
					}
					if (f.getName().equals("amt")) {
						f.set(bq, Double.parseDouble(object.get(f.getName())
								+ ""));
					}
					if (f.getName().equals("action")) {
						f.set(bq,
								Integer.parseInt(object.get(f.getName()) + ""));
					}
					if (f.getName().equals("indbdate")) {
						f.set(bq, object.get(f.getName()));
					}
				}
				list.add(bq);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String toString() {
		return "Beiqing [_id=" + _id + ", id=" + id + ", uid=" + uid + ", gid="
				+ gid + ", sid=" + sid + ", gunion=" + gunion + ", amt=" + amt
				+ ", action=" + action + ", indbdate=" + indbdate
				+ ", webMasterName=" + webMasterName + "]";
	}

}
