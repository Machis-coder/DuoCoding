import { Directive, inject, Input, OnInit } from "@angular/core";
import { isUserInRole, isUserLogged, userRole } from "../utils/user.utils";
import { BaseComponent } from "./base.component";
import { ActivatedRoute, Router } from "@angular/router";
import ConstRoutes from "../shared/contants/const-routes";

@Directive()
export abstract class BasePage extends BaseComponent {
    
    router: Router = inject(Router);
    route: ActivatedRoute = inject(ActivatedRoute);

    ngOnInit(): void {    
        const data = this.route.snapshot.data;
        this.ifUserInRole = data['roles'];
        const show = isUserLogged() && isUserInRole(data['roles']);

        if (!show) {
            this.router.navigate(["/" + ConstRoutes.PATH_FORBIDDEN]);
        }
    }
    
}
