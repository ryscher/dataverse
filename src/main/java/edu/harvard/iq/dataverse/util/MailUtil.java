package edu.harvard.iq.dataverse.util;

import edu.harvard.iq.dataverse.UserNotification;
import edu.harvard.iq.dataverse.branding.BrandingUtil;
import static edu.harvard.iq.dataverse.settings.SettingsServiceBean.Key.SystemEmail;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailUtil {

    private static final Logger logger = Logger.getLogger(MailUtil.class.getCanonicalName());

    public static InternetAddress parseSystemAddress(String systemEmail) {
        if (systemEmail != null) {
            try {
                InternetAddress parsedSystemEmail = new InternetAddress(systemEmail);
                logger.fine("parsed system email: " + parsedSystemEmail);
                return parsedSystemEmail;
            } catch (AddressException ex) {
                logger.info("Email will not be sent due to invalid value in " + SystemEmail + " setting: " + ex);
                return null;
            }
        }
        logger.fine("Email will not be sent because the " + SystemEmail + " setting is null.");
        return null;
    }

    public static String getSubjectTextBasedOnNotification(UserNotification userNotification, String rootDataverseName) {
        List<String> rootDvNameAsList = Arrays.asList(BrandingUtil.getInstallationBrandName(rootDataverseName));
        switch (userNotification.getType()) {
            case ASSIGNROLE:
                return BundleUtil.getStringFromBundle("notification.email.assign.role.subject", rootDvNameAsList);
            case REVOKEROLE:
                return BundleUtil.getStringFromBundle("notification.email.revoke.role.subject", rootDvNameAsList);
            case CREATEDV:
                return BundleUtil.getStringFromBundle("notification.email.create.dataverse.subject", rootDvNameAsList);
            case REQUESTFILEACCESS:
                return BundleUtil.getStringFromBundle("notification.email.request.file.access.subject", rootDvNameAsList);
            case GRANTFILEACCESS:
                return BundleUtil.getStringFromBundle("notification.email.grant.file.access.subject", rootDvNameAsList);
            case REJECTFILEACCESS:
                return BundleUtil.getStringFromBundle("notification.email.rejected.file.access.subject", rootDvNameAsList);
            case MAPLAYERUPDATED:
                return BundleUtil.getStringFromBundle("notification.email.update.maplayer", rootDvNameAsList);
            case MAPLAYERDELETEFAILED:
                return BundleUtil.getStringFromBundle("notification.email.maplayer.deletefailed.subject", rootDvNameAsList);
            case CREATEDS:
                return BundleUtil.getStringFromBundle("notification.email.create.dataset.subject", rootDvNameAsList);
            case SUBMITTEDDS:
                return BundleUtil.getStringFromBundle("notification.email.submit.dataset.subject", rootDvNameAsList);
            case PUBLISHEDDS:
                return BundleUtil.getStringFromBundle("notification.email.publish.dataset.subject", rootDvNameAsList);
            case RETURNEDDS:
                return BundleUtil.getStringFromBundle("notification.email.returned.dataset.subject", rootDvNameAsList);
            case CREATEACC:
                return BundleUtil.getStringFromBundle("notification.email.create.account.subject", rootDvNameAsList);
            case CHECKSUMFAIL:
                return BundleUtil.getStringFromBundle("notification.email.checksumfail.subject", rootDvNameAsList);
            case FILESYSTEMIMPORT:
                return BundleUtil.getStringFromBundle("notification.email.import.filesystem.subject", rootDvNameAsList);
            case CHECKSUMIMPORT:
                return BundleUtil.getStringFromBundle("notification.email.import.checksum.subject", rootDvNameAsList);
        }
        return "";
    }

}
