import { Link } from 'react-router-dom';

export function NotFound() {
    return (
        <div className="min-h-[60vh] flex items-center justify-center">
            <div className="text-center">
                {/* Ícone */}
                <div className="mb-8">
                    <span className="text-8xl">🏓</span>
                </div>

                <h1 className="text-6xl font-bold text-gray-300 mb-4">404</h1>

                <h2 className="text-2xl font-bold text-gray-900 mb-4">
                    Página não encontrada
                </h2>

                <p className="text-gray-600 mb-8 max-w-md mx-auto">
                    A página que você está procurando não existe, foi removida
                    ou está temporariamente indisponível.
                </p>

                <div className="space-x-4">
                    <Link
                        to="/"
                        className="btn-primary inline-block"
                    >
                        Voltar ao início
                    </Link>
                    <Link
                        to="/tournaments"
                        className="btn-secondary inline-block"
                    >
                        Ver torneios
                    </Link>
                </div>

                <div className="mt-12 p-6 bg-gray-50 rounded-lg max-w-md mx-auto">
                    <h3 className="font-medium text-gray-700 mb-3">
                        Talvez você esteja procurando:
                    </h3>
                    <ul className="text-left space-y-2">
                        <li>
                            <Link
                                to="/tournaments"
                                className="text-blue-600 hover:underline"
                            >
                                🏆 Torneios disponíveis
                            </Link>
                        </li>
                        <li>
                            <Link
                                to="/login"
                                className="text-blue-600 hover:underline"
                            >
                                🔑 Fazer login
                            </Link>
                        </li>
                        <li>
                            <Link
                                to="/register"
                                className="text-blue-600 hover:underline"
                            >
                                ✨ Criar conta
                            </Link>
                        </li>
                    </ul>
                </div>

                <p className="mt-8 text-sm text-gray-400 italic">
                    "Até o melhor jogador de tênis de mesa erra um saque às vezes..."
                </p>
            </div>
        </div>
    );
}