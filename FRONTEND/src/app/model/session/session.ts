import { Time } from "@angular/common";
import { Course } from "../course/course";
 

export class Session {
    id?: number;
    name!: String ;
    beginDate?: Date ; 
    endDate?: Date;
    description!: String;
    archive!: boolean;
    courseId!: number| undefined; 
}
