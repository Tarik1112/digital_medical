


export const LoginAPI = async (data) => {
    let res = await fetch("http://localhost:8080/digital_medical/api/doctor/login", {
        method: "POST",
        headers: { "Content-Type": "application/json",
            "Accept": "application/json" },
        body: JSON.stringify(data, null, 2),
    });

    res = await res.json();
    return res;
};


export const RegisterAPI = async (data) => {
    let res = await fetch("http://localhost:8080/digital_medical/api/doctor/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data, null, 2),
    }).then(response => {
        if (response.status === 200) {
            const ress = {
                res: response.json(),
                status: 200
            }
            return ress;
        } else{
            const ress = {
                res: response.json(),
                status: 400
            }
            return ress;
        }
    });
    return res;
};

export const RegisterPatientAPI = async (data) => {
    let res = await fetch("http://localhost:8080/digital_medical/api/doctor/register_patient", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data, null, 2),
    }).then(response => {
        if (response.status === 200) {
            const ress = {
                res: response.json(),
                status: 200
            }
            return ress;
        } else{
            const ress = {
                res: response.json(),
                status: 400
            }
            return ress;
        }
    });
    return res;
};

export const GetCurrentUserAPI = async (token) => {
    let res = await fetch("http://localhost:8080/digital_medical/api/doctor/get-current-user", {
        method: "GET",
        headers: { "Content-Type": "application/json",
            "Authorization": token,
            "Accept": "application/json" },

    }).then((res) => {
        return res.json();
    });
    return res;
}