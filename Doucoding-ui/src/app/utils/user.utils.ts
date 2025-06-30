import { getUser } from "../core/services/utils.service";
import { User } from "../interfaces/user";

export const isUserLogged = () => {
    const user: User = getUser()
    return user && user !== null;
}

export const userRole = () => {
    const user: User = getUser()
    return user && user !== null? user.role: '' ;
}

export const isUserInRole = (roles: string[]) => {
    const userRol = userRole();
    return roles.length === 0 || userRol === '' || roles.indexOf(userRol) > -1;
}