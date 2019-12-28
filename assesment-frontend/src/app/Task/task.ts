import { ParentTask } from "./parenttask";

export class Task{

    constructor(public id:number=0,public parentID: number=0, public task: string , public parentTask : string,public startDate: string , public endDate: string , public priority: number,public parent:ParentTask){
        
    }

}