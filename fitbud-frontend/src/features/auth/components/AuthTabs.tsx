type AuthTabsProps = {
    isLogin: boolean;
    onLogin: () => void;
    onRegister: () => void;
};

export default function AuthTabs({
    isLogin,
    onLogin,
    onRegister,
}: AuthTabsProps) {
    return (
        <div className="logo-font text-lg flex bg-[#1b1f25] rounded-2xl p-1 mb-5">

    <button
        onClick={onLogin}
        className={`flex-1 py-2 rounded-xl font-semibold
        transition-all duration-300 ease-out ${
            isLogin
                ? "bg-lime-400 text-black shadow-[0_0_15px_rgba(163,230,53,0.45)]"
                : "text-gray-400  cursor-pointer"
        }`}
    >
        LOGIN
    </button>

    <button
        onClick={onRegister}
        className={`flex-1 py-2 rounded-xl font-semibold
        transition-all duration-300 ease-out ${
            !isLogin
                ? "bg-lime-400 text-black shadow-[0_0_15px_rgba(163,230,53,0.45)]"
                : "text-gray-400  cursor-pointer"
        }`}
    >
        REGISTER
    </button>

</div>
    );
}