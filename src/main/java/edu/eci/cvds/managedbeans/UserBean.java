package edu.eci.cvds.managedbeans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@Named
@Stateless
@ViewScoped
@ManagedBean(name="userBean",eager =true)
public class UserBean implements Serializable {
    private static final transient Logger log = LoggerFactory.getLogger(UserBean.class);

    private String username;
    private String password;

    public UserBean() {
    }

    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());
            try {
                currentUser.login(token);
                if (currentUser.hasRole("admin")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("administrador.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("regular.xhtml");
                }
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            } catch (AuthenticationException | IOException ae) {
                log.info("Ops something happened.");
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }
}