package top.tinx.blog.utils;

import com.google.common.base.Throwables;
import kamon.sigar.SigarProvisioner;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import top.tinx.blog.bean.Infos;

/**
 * sigar 工具类，自动引用，但是有问题！使用过期注解标注，不能使用！
 */
@Deprecated
public class SigarService {

    private final Sigar sigar;

    public SigarService() {
        Sigar sigar = null;
        try {
            SigarProvisioner.provision();
            sigar = new Sigar();
            // call it to make sure the library was loaded
            sigar.getPid();
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
        this.sigar = sigar;
    }

    public Infos infos() {

        Infos infos = new Infos();
        try {
            //infos.setPid(sigar.getPid());
            //infos.setFqdn(sigar.getFQDN());
            //infos.setHostname(sigar.getNetInfo().getHostName());
            //infos.setUptime(sigar.getUptime().getUptime());

            String[] netInterfaceList = sigar.getNetInterfaceList();
            for (String netInterfaceName : netInterfaceList) {
                // Add net interface
                NetInterfaceConfig config = sigar.getNetInterfaceConfig(netInterfaceName);
                Infos.Interface netInterface = new Infos.Interface();
                //netInterface.setName(config.getName());
                //netInterface.setType(config.getType());
                //netInterface.setAddress(config.getAddress());
                //infos.getInterfaces().add(netInterface);
            }

            // Add cpu
            //infos.getCpu().setSys(sigar.getThreadCpu().getSys());
            //infos.getCpu().setTotal(sigar.getThreadCpu().getTotal());
            //infos.getCpu().setUser(sigar.getThreadCpu().getUser());
//
            //// Add mem
            //infos.getMem().setTotal(sigar.getMem().getTotal());
            //infos.getMem().setUsed(sigar.getMem().getUsed());
            //infos.getMem().setFree(sigar.getMem().getFree());

            // Add swap
            //infos.getSwap().setTotal(sigar.getSwap().getTotal());
            //infos.getSwap().setUsed(sigar.getSwap().getUsed());
            //infos.getSwap().setFree(sigar.getSwap().getFree());

        } catch (SigarException e) {
            throw Throwables.propagate(e);
        }

        return infos;
    }
}
