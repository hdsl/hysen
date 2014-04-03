package hysen.ejb.services;

import hysen.ejb.entities.ClientProduct;
import hysen.ejb.entities.GeneratePk;
import hysen.ejb.entities.ServiceRequest;
import hysen.ejb.entities.UserAccount;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Stateless
public class CustomCrudService {

    @PersistenceContext
    private EntityManager em;

    public List findByParameter(Class t, String parameterQuery, String productTypeId, Character includeLogicallyDelete) {

        String qry = "SELECT t FROM " + t.getSimpleName() + " t "
                + "WHERE t." + parameterQuery + " LIKE '%" + productTypeId + "%' "
                + "AND t.deleted='" + includeLogicallyDelete + "' ";

        try {

            return em.createQuery(qry).getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            return Collections.EMPTY_LIST;

        }
    }

    public List<ClientProduct> clientProductsList(String clientId, String productType) {

        List<ClientProduct> productList;

        String qry = "SELECT s FROM ClientProduct s WHERE s.clientDetail.commonId = '" + clientId + "' "
                + "AND s.productTypeModel.productTypes.commonId = '" + productType + "' AND s.deleted = 'N'";

        try {

            productList = em.createQuery(qry).getResultList();

            return productList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

//    public ClientProduct clientServiceModel(String serialNumber) {
//
//        String qry = "SELECT s FROM ClientProduct s WHERE s.serialNumber = '" + serialNumber + "' AND s.deleted = 'N'";
//
//        try {
//
//            return (ClientProduct) em.createQuery(qry).getSingleResult();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public List<ServiceRequest> clientServiceRequestList(Date startDate, Date endDate, String clientId) {

        List<ServiceRequest> serviceRequestList;

        String qry = "SELECT t FROM ServiceRequest t WHERE t.clientDetail.commonId = '" + clientId + "' "
                + "AND t.requestDate BETWEEN :startDate AND :endDate AND t.deleted='N' "
                + "ORDER BY t.clientProduct.serialNumber ";

        try {

            serviceRequestList = em.createQuery(qry).setParameter("startDate", startDate, TemporalType.TIMESTAMP).setParameter("endDate", endDate, TemporalType.TIMESTAMP).getResultList();

            return serviceRequestList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

    }

    public List<ServiceRequest> clientServiceModelRequestList(Date startDate, Date endDate, String serialNumber) {

        List<ServiceRequest> serviceRequestList;

        String qry = "SELECT t FROM ServiceRequest t WHERE t.clientProduct.commonId = '" + serialNumber + "' "
                + "AND t.requestDate BETWEEN :startDate AND :endDate AND t.deleted='N' "
                + "ORDER BY t.requestDate ";

        try {

            serviceRequestList = em.createQuery(qry).setParameter("startDate", startDate, TemporalType.TIMESTAMP).setParameter("endDate", endDate, TemporalType.TIMESTAMP).getResultList();

            return serviceRequestList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

    }

    public ServiceRequest clientPMScheduleList(String pmPeriod, int pmYear, String serialNumber) {

        String qry = "SELECT t FROM ServiceRequest t WHERE t.clientProduct.commonId = '" + serialNumber + "' "
                + "AND t.pmPeriod = '" + pmPeriod + "' AND t.pmYear='" + pmYear + "' AND t.deleted='N' "
                + "ORDER BY t.serviceStartDate ";

        try {

            return (ServiceRequest) em.createQuery(qry).getSingleResult();

        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }

    }

    public UserAccount getUserAccount(String username, String password) {

        String qry = "SELECT t FROM UserAccount t WHERE t.username = '" + username + "' AND t.userPassword = '" + password + "' "
                + "AND t.deleted = 'N'";

        try {

            return (UserAccount) em.createQuery(qry).getSingleResult();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    // <editor-fold defaultstate="collapsed" desc=" GeneratePk Persistence Functionalities">
    public boolean generatePkUpdate(GeneratePk generatePk) {
        try {

            em.merge(generatePk);
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public Integer generatePk(String pkName) {
        try {

            GeneratePk generatePk = em.find(GeneratePk.class, pkName);

            int in = generatePk.getPkValue();
            int a = 1 + in;

            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public GeneratePk getGenPk(String pkName) {
        try {

            GeneratePk generatePk = em.find(GeneratePk.class, pkName);

            return generatePk;

        } catch (Exception e) {
            return null;
        }
    }

    // </editor-fold>
}
