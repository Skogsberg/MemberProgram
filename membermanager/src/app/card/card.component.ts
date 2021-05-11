import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { AppComponent } from '../app.component';
import { Member } from '../member/member';
import { MemberService } from '../service/member.service';


@Component({
    selector: 'card',
    templateUrl: './card.component.html',
    styleUrls: ['./card.component.css']
  })

  export class CardComponent {
      @Input() public members: Member[] = []

      constructor(
        private readonly memberService: MemberService, 
        private readonly appComponent: AppComponent,
      ) {}

      public deleteMember(member: Member): void {
        this.memberService.deleteMembers(member).subscribe(
          (response: void) => {
            this.appComponent.members = [];
            this.appComponent.getMembers();
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          })
      }
     
  }