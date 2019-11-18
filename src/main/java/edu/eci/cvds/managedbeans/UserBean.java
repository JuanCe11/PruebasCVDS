package edu.eci.cvds.managedbeans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;

@Named
@Stateless
@SessionScoped
@ManagedBean(name = "userBean", eager = true)
public class UserBean implements Serializable {
    private static final transient Logger log = LoggerFactory.getLogger(UserBean.class);

    private String username;
    private String password;
    private String principalUrl = "/faces/index.xhtml";
    Subject subject;

    public UserBean() {
    }

    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), new Sha256Hash(getPassword()).toHex());
        try {
            subject.login(token);
            if (subject.hasRole("administrador")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/admin/pages/cambiarEstadoRecurso.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/consultarRecursos.xhtml");
            }
        } catch (UnknownAccountException ex) {
            facesMessage("There is no user with username of " + token.getPrincipal());
        } catch (IncorrectCredentialsException ex) {
            facesMessage("Password for account " + token.getPrincipal() + " was incorrect!");
        } catch (LockedAccountException ex) {
            facesMessage("The account for username " + token.getPrincipal() + " is locked. Please contact your administrator to unlock it.");
        } catch (Exception ex) {
            log.info("Ops something happened." + ex.getMessage());
            facesMessage("Ops something happened." + ex.getMessage());
        } finally {
            token.clear();
        }
    }

    private void facesMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public void doLogOut() {

        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(principalUrl);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
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
