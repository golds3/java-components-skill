import request from '@/utils/request'


export const getBase64VerCode =()=>{
    return request.get('/api/checkCode')
}

export const sendEmailVerCode =(obj)=>{
    console.log(obj)
    return request.post('/api/sendEmailCode',obj)

}