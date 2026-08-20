import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export function Navbar() {
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <nav className="bg-white shadow-sm border-b">
            <div className="max-w-7xl mx-auto px-4">
                <div className="flex justify-between items-center h-16">
                    <Link to={user ? "/dashboard" : "/"} className="flex items-center space-x-2">
                        <span className="text-2xl">🏓</span>
                        <span className="font-bold text-xl text-gray-900">Tênis Mesa</span>
                    </Link>

                    <div className="flex items-center space-x-4">
                        {user ? (
                            <>
                                <Link to="/tournaments" className="text-gray-600 hover:text-gray-900">
                                    Torneios
                                </Link>
                                <span className="text-gray-400">|</span>
                                <span className="text-gray-700">{user.nome}</span>
                                <button
                                    onClick={handleLogout}
                                    className="btn-secondary text-sm"
                                >
                                    Sair
                                </button>
                            </>
                        ) : (
                            <>
                                <Link to="/login" className="text-gray-600 hover:text-gray-900">
                                    Entrar
                                </Link>
                                <Link to="/register" className="btn-primary text-sm">
                                    Cadastrar
                                </Link>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}