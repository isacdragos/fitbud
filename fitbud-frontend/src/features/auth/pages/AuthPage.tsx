import { useState } from "react";

import AuthLayout from "../components/AuthLayout";
import AuthTabs from "../components/AuthTabs";
import LoginForm from "../components/LoginForm";
import RegisterForm from "../components/RegisterForm";

export default function AuthPage() {
    const [isLogin, setIsLogin] = useState(true);

    return (
        <AuthLayout>
            <AuthTabs
                isLogin={isLogin}
                onLogin={() => setIsLogin(true)}
                onRegister={() => setIsLogin(false)}
            />

            {isLogin ? <LoginForm /> : <RegisterForm />}
        </AuthLayout>
    );
}