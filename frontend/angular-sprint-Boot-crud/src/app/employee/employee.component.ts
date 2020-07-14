import { Component, OnInit } from '@angular/core';
import {EmployeeServiceService} from '../shared/employee-service.service';
import { NgForm } from '@angular/forms';
import {Employee} from '../shared/Employee';
// import { ToastrService } from 'ngx-toastr';

declare var M : any;
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  constructor(public empservice : EmployeeServiceService,
   ) { }

  ngOnInit(): void {
    this.resetForm();
    this.empList();
  }
  resetForm(form?: NgForm){
    if (form!=null)
    form.resetForm();
    this.empservice.selectedEmployee={
      id:'',
      name:'',
      position:'',
      office: '',
      salary:null
   }
 }

  onSubmit(form:NgForm){
    if(!form.value.id){
      this.empservice.regEmployee(form.value).subscribe((res)=>{
        this.resetForm(form);
        this.empList();

      });
      // this.toastr.success("Registered successfully")
      M.toast({html: 'registered successfully',classes:'rounded'});


    }
    else{
      this.empservice.putEmployee(form.value).subscribe((res)=>{
        this.resetForm(form);
        this.empList();

      });
      // this.toastr.success("Updated successfully")
      M.toast({html: 'Updated successfully',classes:'rounded'});


    }

  }

  empList(){
    this.empservice.getEmpolyees().subscribe((res)=>{
      this.empservice.employees=res as Employee[];
      console.log(res)
    })
  }

  onEdit(emp : Employee){
    this.empservice.selectedEmployee = emp;
  }

  onDelete(id:string,form:NgForm){
      if(confirm("Are you sure to delete that record?")==true){
        this.empservice.empDelete(id).subscribe((res)=>{
          this.empList();
          this.resetForm(form);

        });
        // this.toastr.success("Delete successfully")
        M.toast({html: 'Delete successfully',classes:'rounded'});
      }

  }

}
