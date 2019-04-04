package hib;

import javax.persistence.Entity;

import org.hibernate.cfg.Configuration;

import com.pancou.ad.vo.AdBox;
import com.pancou.ad.vo.AdBoxCount;
import com.pancou.ad.vo.AdBoxInfo;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdCreativeLog;
import com.pancou.ad.vo.AdCreativeType;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.AdSize;
import com.pancou.ad.vo.Chinaarea;
import com.pancou.ad.vo.Cms;
import com.pancou.ad.vo.CmsClass;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.CycleMoneyReceipt;
import com.pancou.ad.vo.Ec;
import com.pancou.ad.vo.FileInstance;
import com.pancou.ad.vo.FileInstanceDir;
import com.pancou.ad.vo.InterfaceBeiqing;
import com.pancou.ad.vo.InterfaceTaoXie;
import com.pancou.ad.vo.Log;
import com.pancou.ad.vo.LoginIp;
import com.pancou.ad.vo.MediaIter;
import com.pancou.ad.vo.Models;
import com.pancou.ad.vo.Operation;
import com.pancou.ad.vo.Pay;
import com.pancou.ad.vo.PayType;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.Resource;
import com.pancou.ad.vo.Role;
import com.pancou.ad.vo.RoleResource;
import com.pancou.ad.vo.Service;
import com.pancou.ad.vo.Shop;
import com.pancou.ad.vo.ShopCount;
import com.pancou.ad.vo.ShopFclass;
import com.pancou.ad.vo.ShopIndex;
import com.pancou.ad.vo.ShopSclass;
import com.pancou.ad.vo.ShopSource;
import com.pancou.ad.vo.ShopWeb;
import com.pancou.ad.vo.SourcePhoto;
import com.pancou.ad.vo.Token;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.UrlAllowabled;
import com.pancou.ad.vo.UrlRefuse;
import com.pancou.ad.vo.UserGroup;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.ViewAdBoxCount;
import com.pancou.ad.vo.ViewReadyBox;
import com.pancou.ad.vo.WebMaster;

@Entity
public class BaseHibernateClassImport implements HibernateImport {

	public void build(Configuration configuration) {
		configuration.addClass(AdBox.class);
		configuration.addClass(AdPlan.class);
		configuration.addClass(AdPlanCycle.class);
		configuration.addClass(Customer.class);
		configuration.addClass(ReadyPlan.class);
		configuration.addClass(Resource.class);
		configuration.addClass(Role.class);
		configuration.addClass(RoleResource.class);
		configuration.addClass(UrlAddress.class);
		configuration.addClass(UserRole.class);
		configuration.addClass(Users.class);
		configuration.addClass(WebMaster.class);
		configuration.addClass(Service.class);
		configuration.addClass(Cms.class);
		configuration.addClass(CmsClass.class);
		configuration.addClass(AdSize.class);
		configuration.addClass(Models.class);
		configuration.addClass(AdBoxInfo.class);
		configuration.addClass(AdCreative.class);
		configuration.addClass(ReadyBox.class);
		configuration.addClass(ReportCount.class);
		configuration.addClass(ViewAdBoxCount.class);
		configuration.addClass(Pay.class);
		configuration.addClass(PayType.class);
		configuration.addClass(Chinaarea.class);
		configuration.addClass(Operation.class);
		configuration.addClass(AdBoxCount.class);
		configuration.addClass(SourcePhoto.class);
		configuration.addClass(UrlRefuse.class);
		configuration.addClass(Log.class);
		configuration.addClass(UserGroup.class);
		configuration.addClass(AdCreativeLog.class);
		configuration.addClass(UrlAllowabled.class);
		configuration.addClass(LoginIp.class);
		configuration.addClass(AdCreativeType.class);
		configuration.addClass(Token.class);
		configuration.addClass(Ec.class);
		configuration.addClass(MediaIter.class);
		configuration.addClass(ViewReadyBox.class);

		// Interface for Ec
		configuration.addClass(InterfaceTaoXie.class);
		configuration.addClass(InterfaceBeiqing.class);

		/** wap **/
		configuration.addClass(ShopIndex.class);
		configuration.addClass(ShopFclass.class);
		configuration.addClass(ShopSclass.class);
		configuration.addClass(ShopWeb.class);
		configuration.addClass(Shop.class);
		configuration.addClass(ShopCount.class);
		configuration.addClass(ShopSource.class);
		configuration.addClass(FileInstance.class);
		configuration.addClass(FileInstanceDir.class);
		/** wap **/

		// 20160510　新增
		configuration.addClass(CycleMoneyReceipt.class);
	}

}
