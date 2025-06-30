import { Directive, Input, OnInit } from "@angular/core";
import { isUserInRole } from "../utils/user.utils";


@Directive()
export abstract class BaseComponent implements OnInit {
    @Input() ifUserInRole: string[] = [];

    show: boolean = true;
    
    constructor() {
        
    }

    ngOnInit(): void {    
        this.show = this.mustShow()
    }

    mustShow() : boolean {
        return isUserInRole(this.ifUserInRole);
    }
}