export interface JwtToken {
        auth: [{authority:string}],
        sub: string,
        exp: any
}
