


export const LoginAPI = async (data) => {
    let res = await fetch("http://localhost:8080/api/doctor/login", {
        method: "POST",
        headers: { "Content-Type": "application/json",
            "Accept": "application/json" },
        body: JSON.stringify(data, null, 2),
    });

    res = await res.json();
    return res;
};

export const RegisterAPI = async (data) => {
    let res = await fetch("http://localhost:8080/api/doctor/register", {
        method: "POST",
        headers: { "Content-Type": "application/json",
            "Accept": "application/json" },
        body: JSON.stringify(data, null, 2),
    });

    res = await res.json();
    return res;
};