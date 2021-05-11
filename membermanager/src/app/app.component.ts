import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Member } from './member/member';
import { MemberService } from './service/member.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
	public members: Member[] = [];

	constructor(private memberService: MemberService) { }

	ngOnInit() {
		this.getMembers();
	}

	public getMembers(): void {
		this.memberService.getMembers().subscribe(
			(response: Member[]) => {
				console.log();
				this.members = response;
			},
			(error: HttpErrorResponse) => {
				alert(error.message);
			}
		);
	}
}
