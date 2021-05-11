import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Member } from "../member/member";


@Injectable({
    providedIn: 'root'
})
export class MemberService {
    private apiServerUrl: string = environment.apiBaseUrl;

    constructor(private http:  HttpClient) { }

    public getMembers(): Observable<Member[]> {
        return this.http.get<Member[]>(`${this.apiServerUrl}/member/all`);
    }

    public addMembers(member: Member): Observable<Member> {
        return this.http.post<Member>(`${this.apiServerUrl}/member/add`, member);
    }

    public updateMembers(member: Member): Observable<Member> {
        return this.http.put<Member>(`${this.apiServerUrl}/member/update`, member);
    }

    public deleteMembers(member: Member): Observable<void> { 
        return this.http.delete<void>(`${this.apiServerUrl}/member/delete/${member.id}`);
    }

}