#!/bin/sh

if [ -n "$VITE_API_URL" ]; then
    echo "Configurando API URL: $VITE_API_URL"
    find /usr/share/nginx/html -type f -name "*.js" -exec \
        sed -i "s|__VITE_API_URL__|${VITE_API_URL}|g" {} \;
fi

# Executar o comando passado (nginx)
exec "$@"